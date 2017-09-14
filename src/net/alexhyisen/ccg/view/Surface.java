package net.alexhyisen.ccg.view;

import net.alexhyisen.ccg.model.Blob;
import net.alexhyisen.ccg.model.Property;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Surface {
    private static void cli() {
        Blob[] blobs = new Blob[]{new Blob(), new Blob()};
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("source path = ");
            blobs[0].load(Paths.get(cin.readLine()));
            System.out.print("target path = ");
            blobs[1].load(Paths.get(cin.readLine()));;

            Set<Property> properties = new HashSet<>();
            properties.addAll(blobs[0].getProperties());
            properties.retainAll(blobs[1].getProperties());
            System.out.println("\nCommon Properties");
            properties.forEach(System.out::println);

            System.out.print("\nsource variant = ");
            String source = cin.readLine();
            System.out.print("\ntarget variant = ");
            String target = cin.readLine();
            System.out.println("\nGenerated Code");
            properties.stream()
                    .map(v -> v.genCopyCode(source, target))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        cli();
    }
}
