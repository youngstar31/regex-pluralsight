import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

public class playpen {

    public static void testStringMethods() {
        String string = "x and tigers and bears. oh my!";
        String replaced = string.replaceAll("q","Lions");
        System.out.println(string.equals(replaced));
    }

    public static void validateMasterCardNumber() {
        Pattern pattern = Pattern.compile("^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$");
//        boolean matches;
//        matches = pattern.matcher("5112345678890123").matches();
//        System.out.println(matches);
//        matches = pattern.matcher( "2221012345678901").matches();
//        System.out.println(matches);
//        matches = pattern.matcher("2721012345678901").matches();
//        System.out.println(matches);

        Collection <String> numbers = Arrays.asList(
                "5112345678890123",
                "2221012345678901",
                "2721012345678901");

       List<String> valids = numbers.stream().filter(pattern.asPredicate()).collect(Collectors.toList());
        System.out.println(valids);

    }

    public static void invalidRegex() {
//        Pattern pattern = Pattern.compile("\\w\\d[]");
        try {
            "some-string".matches("\\w\\d[]");
        } catch (PatternSyntaxException exception) {
            System.out.println("Index: " + exception.getIndex());
            System.out.println("Pattern:" + exception.getPattern());
            System.out.println("Message: " + exception.getMessage());
        }

    }

    public static void testEscapeCharacter() {
        ("I favor the numbers \"22\" and 34, not to mention the \\ character" + "\n\r A is octal \101").replaceFirst("\\d" +
                "\\d","4");
    }

    private static void display(Pattern pattern, String group) {
        Matcher matcher = pattern.matcher(group);
        int count = matcher.groupCount(); // get the group count
        //must check for a macth
        if (matcher.matches())  {

            //groups are numbered from zero, up to and including the count
            for (int i = 0; i <= count + 1; i++) {
                System.out.printf("%s.%s%n", i, matcher.group(i));
            }
        }
    }

    public static void testCaptureGroup() {
        Pattern pattern = Pattern.compile("(\\w+)(-(\\w+))+-(\\w+)");
        display(pattern, "securities-development-equities-valuation-americas");
        display(pattern, "fixed_income-development-equities-asia");
        display(pattern, "fx-development-emea");
    }

    public static void testNamedGroup() {
        Pattern pattern = Pattern.compile("(?<business>\\w+)(-(\\w+))+-(?<region>\\w+)");
        displayGroups(pattern, "securities-development-equities-valuation-americas", "business", "region");
        displayGroups(pattern, "fixed_income-development-equities-asia","business", "region" );
        displayGroups(pattern, "fx-development-emea", "business", "region");
    }

    private static void displayGroups(Pattern pattern, String label, String... groupNames) {
        Matcher matcher = pattern.matcher(label);
        if (matcher.matches()) {
            System.out.println(matcher.group(0));
            for (String name : groupNames) {
                System.out.printf("%s: %s%n", name, matcher.group(name));
            }
        }
    }

    public static void testReplaceAllWithReferences() {
        List<String> list = Arrays.stream(new String[] {
            "securities-development-equities-valuation-americas",
                    "securities-development-equities-valuation-americas",
                    "fixed_income-development-equities-asia",
                "fx-development-emea"}).map(
                        label -> label.replaceAll("(?<business>\\w+)(-(\\w+))+-(?<region>\\w+)", "Region:${region}, Unit:$1")).collect(Collectors.toList());
        for (String line : list) {
            System.out.println(line);



        }
    }

    public static void test() {
        String text = "The \"rain\" in Spain \"falls\" mainly on \"the plain\"";
        Pattern pattern = Pattern.compile("(\"\\w+\")|(\\w+)");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Group 1: " + matcher.group(1) + ";");
            System.out.println("Group 1: " + matcher.group(2));
        }
    }

//    public static void testDotAll() {
//        String regex = "abc.+def";
//        String text = "abc\r\ndef";
//        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
//        Matcher matcher = pattern.matcher(text);
//        if (matcher.find()) {
//            System.out.println("Match!");
//            return;
//        }
//        fail("No match");
//    }

    public static void testMatches() {
        boolean matches = Pattern.matches("[\\w\\s]+", "She sells sea shells");
        System.out.println(matches);
    }

    public static void testSplitAsStream() {
        String text = "One, Two,  , Buckle,   , My_Shoe";
        Pattern pattern = Pattern.compile("(,\\s+)");
        List<String> list = pattern.splitAsStream(text).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(list);
    }

    public static void matcherClass(){
        String text = "Hello, world";
//        Pattern pattern = Pattern.compile("(?<word>\\w+)");
//        Matcher matcher = pattern.matcher(text);
//        while (matcher.find()) {
//            System.out.printf("Found '%s'%n", matcher.group(0));
//            System.out.printf("Found '%s'%n", matcher.group( ));
//            System.out.printf("Found '%s'%n", matcher.group(1));
//            System.out.printf("Found '%s'%n", matcher.group("word"));
//            System.out.println(matcher.start(1) + "," + matcher.end(1));
//            System.out.println(matcher.start("word") + "," + matcher.end("word"));
//        }
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(text);
//        boolean found = matcher.find(2);// start scan at text position 2
//        while (found) {
//            System.out.printf("Found '%s'%n", matcher.group(0));
//            found = matcher.find();
//        }
        matcher.region(2, text.length());
        while (matcher.find()) {
            System.out.println(matcher.toString());
            System.out.println("Found '%s'%n", matcher.group(0));
        }

    }

    public static void main(String[] args) {
//        System.out.println("abcdefg".matches(".b.d.f."));
//        System.out.println(Arrays.asList("23,42,64,hike".split(",")));
//        System.out.println(Arrays.asList("Anne of the 1000 days".split(" ")));
//        System.out.println(Arrays.asList("Anne of the 1000 days".split("xxx")));
//        System.out.println(Arrays.asList("Anne of the 1000 days".split("")));
//        System.out.println(Arrays.asList("Anne of the 1000 days".split(".")));
//
//
//        System.out.println(Arrays.asList("one + one = 2".replaceAll("on","1")));
//
//        testStringMethods();
//        validateMasterCardNumber();
//        invalidRegex();
//        testCaptureGroup();
//        testReplaceAllWithReferences();
//        test();
//        testMatches();
        testSplitAsStream();
        matcherClass();
    }


}
