def init_metadata(cfg, output_dir):
    import json
    import sys
    import os
    import tempfile
    import glob
    import subprocess
    import shutil
    import time

    BUNDLE_CONFIG_SECTION = "bundleConfig"

    OPT_BASE_DIR = "workspaceBaseDir"
    OPT_TOP_TEMPLATE = "topTemplate"
    OPT_COMPONENTS = "components"

    OPT_ITEM_TEMPLATE = "itemTemplate"
    OPT_TYPE = "type"
    OPT_REQ_VERSION = "requiredVersion"
    OPT_MIN_VERSION = "minVersion"
    OPT_MAX_VERSION = "maxVersion"
    OPT_RUNTIME_DEPS = "runtimeDependencies"

    class MetadataProcessor:
        def __init__(self, cfg, output_dir):
            bb.debug(1, "Metadata processor")

            self.__cfg = cfg
            self.__components = list()
            self.__components_data = list()

            self.__temp = output_dir

            if not self.__temp:
                self.__temp = tempfile.mkdtemp()

                bb.debug(1, 
                    "Create tmp dir for metadata generator, {}".format(self.__temp))
            else:
                self.__cleanup()

            top_template_file = cfg.get(
                BUNDLE_CONFIG_SECTION, OPT_TOP_TEMPLATE)
            top_template_path = os.path.join(
                self.get_basedir(), top_template_file)

            with open(top_template_path) as top_template:
                self.__top_template = json.load(top_template)

                bb.debug(1, "Top template {}".format(
                    str(self.__top_template)))

                self.__update_values(
                    BUNDLE_CONFIG_SECTION, self.__top_template)

                bb.debug(1, "Top metadata {}".format(
                    str(self.__top_template)))

            for component in cfg.get(BUNDLE_CONFIG_SECTION, OPT_COMPONENTS).split():
                self.__components.append(component)
                self.__load_component(component)

            bb.debug(1, "Components in bundle {}".format(self.__components))

        def __cleanup(self):
            shutil.rmtree(self.__temp, ignore_errors=True)
            os.makedirs(self.__temp)

        def __process_deps(self, current_id, value):
            template = list()

            for dep in value.split():
                dep_items = dep.split(";")
                id = dep_items[0]

                if id == current_id:
                    raise RuntimeError(
                        "ivalid dependency id: {}".format(id))

                if not cfg.has_section(id):
                    raise RuntimeError(
                        "unknown component: {}".format(id))

                dep_item = {"id": id}

                for item in dep_items[1:]:
                    name, value = item.partition("=")[:: 2]

                    dep_item[name] = value

                template.append(dep_item)

            return template

        def __update_values(self, section, template):
            keys_to_delete = list()

            for key, value in template.items():
                if isinstance(value, dict):
                    self.__update_values(section, value)

                    if not value:
                        keys_to_delete.append(key)

                elif self.__cfg.has_option(section, key):
                    if key == OPT_RUNTIME_DEPS:
                        template[key] = self.__process_deps(section,
                                                            self.__cfg.get(section, key))
                    else:
                        template[key] = self.__cfg.get(section, key)
                elif isinstance(value, str):
                    if value == "optional":
                        keys_to_delete.append(key)
                    elif value == "required":
                        raise RuntimeError(
                            "required field {} is not set".format(key))

            for key in keys_to_delete:
                del template[key]

            reqVersionFound = False
            minMaxVersionFound = False

            for key, value in template.items():
                if key == OPT_REQ_VERSION:
                    reqVersionFound = True

                if key == OPT_MIN_VERSION or key == OPT_MAX_VERSION:
                    minMaxVersionFound = True

            if reqVersionFound and minMaxVersionFound:
                raise RuntimeError(
                    "required version should not be set with min or max version")

        def __load_component(self, component):
            bb.debug(1, "Load {} item".format(component))

            item_template_file = self.__cfg.get(component, OPT_ITEM_TEMPLATE)
            item_template_path = os.path.join(
                self.get_basedir(), item_template_file)

            with open(item_template_path) as item_file:
                item_template = json.load(item_file)
                item_template['id'] = component

                bb.debug(1, 
                    "Component {} template {}".format(component, str(item_template)))

                self.__update_values(component, item_template)

                bb.debug(1, "Component {} metadata {}".format(component,
                                                                str(item_template)))

                self.__components_data.append(item_template)

        def get_components(self):
            return self.__components

        def write(self):
            self.__top_template[OPT_COMPONENTS] = self.__components_data

            bb.debug(1, "Write metadata {}".format(str(self.__top_template)))

            with open('{}/metadata.json'.format(self.__temp), 'w') as outfile:
                json.dump(self.__top_template, outfile, indent=4)

        def get_bundlepath(self):
            return self.__temp

        def get_type(self, component):
            if self.__cfg.has_option(component, OPT_TYPE) is not True:
                return ""

            return self.__cfg.get(component, OPT_TYPE)

        def get_basedir(self):
            if self.__cfg.has_option(BUNDLE_CONFIG_SECTION, OPT_BASE_DIR) is not True:
                return ""

            return self.__cfg.get(BUNDLE_CONFIG_SECTION, OPT_BASE_DIR)

    return MetadataProcessor(cfg, output_dir)

EXPORT_FUNCTIONS init_metadata
