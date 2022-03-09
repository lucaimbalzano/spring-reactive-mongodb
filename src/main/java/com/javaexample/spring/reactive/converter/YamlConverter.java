package com.javaexample.spring.reactive.converter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

@Slf4j
public class YamlConverter {
    public static void main(String[] args) throws IOException {
        log.debug("\n\n\n#### START CONVERTING YAML ####");
        Yaml yaml = new Yaml();
        String path = "/home/whngu/Documents/git/git-workspaces/Workspace-my-github/spring-reactive-mongodb/src/main/java/com/javaexample/spring/reactive/converter/bootstrap.yaml";
        try (InputStream in = Files.newInputStream(Paths.get(path))) {

            TreeMap<String, Map<String, Object>> config = yaml.loadAs(in, TreeMap.class);
            System.out.println(String.format("\n#### FILE YAML in LINE: "+"\n%s%n\n"+"#### "+"CONVERTED into PROPERTIES:%n%n%s", config.toString(), toProperties(config)==null ? "toProperties(config) is NULL ::: ERROR" : toProperties(config)));
        }
        catch (Exception e){
            log.error("-------------- ERROR OCCURRED ------------");
            log.error(e.getMessage());
            e.printStackTrace();
            log.error("-------------- -------------- ------------");
        }
        log.debug("\n#### END YAML CONVERTED ####");
    }

    private static String toProperties(TreeMap<String, Map<String, Object>> config) {

        StringBuilder sb = new StringBuilder();

        for (String key : config.keySet()) {

            sb.append(toString(key, config.get(key)));

        }

        return sb.toString();
    }

    private static String toString(String key, Object mapr) {

        StringBuilder sb = new StringBuilder();

        if(!(mapr instanceof Map)) {
            sb.append(key+"="+mapr+"\n");
            return sb.toString();
        }

        Map<String, Object> map = (Map<String, Object>)mapr;

        for (String mapKey : map.keySet()) {

            if (map.get(mapKey) instanceof Map) {
                sb.append(toString(key+"."+mapKey, map.get(mapKey)));
            } else {
                sb.append(String.format("%s.%s=%s%n", key, mapKey, map.get(mapKey).toString()));
            }
        }

        return sb.toString();
    }
}