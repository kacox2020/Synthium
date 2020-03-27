<?php

class Config
{
    static private function loadConfig() {
        $config = parse_ini_file("/home/stu/kcox18/www/Synthium/WebService/config.cfg", true);
        return $config;
    }

    static public function getConfigValue($stanza, $key) {
        $config = self::loadConfig();
        return $config[$stanza][$key];
    }

}


