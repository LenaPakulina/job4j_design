package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                String error = String.format("Error: This argument '%s' does not start with a '-' character", arg);
                throw new IllegalArgumentException(error);
            }
            if (!arg.contains("=")) {
                String error = String.format("Error: This argument '%s' does not contain an equal sign", arg);
                throw new IllegalArgumentException(error);
            }
            if (arg.indexOf("=") == (arg.length() - 1)) {
                String error = String.format("Error: This argument '%s' does not contain a value", arg);
                throw new IllegalArgumentException(error);
            }
            String key = arg.substring(1, arg.indexOf("="));
            String value = arg.substring(arg.indexOf("=") + 1);
            if (key.isBlank()) {
                String error = String.format("Error: This argument '%s' does not contain a key", arg);
                throw new IllegalArgumentException(error);
            }
            if (value.isBlank()) {
                String error = String.format("Error: This argument '%s' does not contain a value", arg);
                throw new IllegalArgumentException(error);
            }
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
