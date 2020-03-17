package com.igor.course.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Dieser Parser nimmt einen Text, wo die zukunftige Lücken mit @ bezeichnet sind
 * Er überschreibt diese Wörter mit input-tags
 * Es gibt keiner Begrenzung von Wörter in einer Lücke
 * */
public class TextParser {
    private static ArrayList<String> answers;

    public static String parse(String text) {
        answers = new ArrayList<>();

        ArrayList<String> arr = new ArrayList<>(Arrays.asList(text.split("(?<=@)|(?=@)")));
        for (int i = 0; i < arr.size(); i++) {

            if(arr.get(i).equals("@")) {
                arr.remove(i);
                arr.remove(i+1);

                answers.add(arr.get(i));

                String tag = "<input type=\"text\" name=\"form" + answers.size() + "\" required/>";
                arr.set(i, tag);
            }
        }

        return toText(arr);
    }

    private static String toText(ArrayList<String> arr) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String str : arr) {
            stringBuilder.append(str);
        }

        return stringBuilder.toString();
    }

    private static boolean lastCharIsletter(StringBuilder str) {
        char c = str.toString().charAt(str.length()-1);
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static void main(String[] args) {
        String text = "Es war im März 1971, da blickte eine orangefarbene Maus aus dem Fernsehapparat in die Wohnzimmer der Bundesrepublik. Etwas traurig @sah sie aus@, die Augenlider halb geschlossen, die Mundwinkel nach unten verzogen, die Pfoten unbeholfen vor dem Bauch zusammengefaltet. Ratlos war sie, die Maus, denn vor ihr @lag@ ein Haufen ungeordneter Buchstaben. Ein heilloses Durcheinander. Doch die Maus wäre nicht die Maus, wenn ihr da nichts einfiele. Zu Rock'n'Roll-Takten tanzend wirbelte sie die Buchstaben durcheinander. Und siehe da, sie fügten sich wie von selbst zum Titel \"Lach- und Sachgeschichten\".";

        System.out.println(parse(text));
    }

    public static ArrayList<String> getAnswers() {
        return answers;
    }

    public static ArrayList<String> getAnswers(String text) {
        parse(text);
        return answers;
    }
}
