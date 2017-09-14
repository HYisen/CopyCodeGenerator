package net.alexhyisen.ccg.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//Blob is the avatar of a java class file.
public class Blob {
    private List<Property> properties;
    private static Pattern PROPERTY_PATTERN = Pattern.compile("private (.*?) (.*?);");

    public void load(Path path) throws IOException {
        properties = Files.lines(path)
                .map(String::trim)
//                .peek(System.out::println)
                .map(PROPERTY_PATTERN::matcher)
                .filter(Matcher::find)
//                .peek(v -> System.out.println(v.group(0)))
                .map(v -> new Property(v.group(1), v.group(2)))
                .peek(v -> System.out.println(v.getType() + " : " + v.getName()))
                .collect(Collectors.toList());
    }

    public List<Property> getProperties() {
        return properties;
    }

    public static void main(String[] args) {
        Blob b = new Blob();
        try {
            b.load(Paths.get("C:\\Users\\alexh\\Documents\\Code\\eden\\wdhis-emis\\wdhis-emis-model\\src\\main\\java\\com\\wdhis\\emis\\dto\\ineconomy\\PatientDetailsDto.java"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
