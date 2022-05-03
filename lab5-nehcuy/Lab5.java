import cs2030s.fp.Maybe;
import cs2030s.fp.Transformer;
import java.util.Map;

class Lab5 {
  /*
     public static String getGrade(String student, String module, String assessment,
         Map<String, Map<String, Map<String, String>>> db) {
           Map<String, Map<String, String>> std = db.get(student);
           if (std == null) {
             return "No such entry";
           } else {
             Map<String, String> mod = std.get(module);
             if (mod == null) {
               return "No such entry";
             } else {
               String grade = mod.get(assessment);
               if (grade == null) {
                 return "No such entry";
               }
                 return grade;
             }
           }
          }
   */

  // Re-implemented getGrade method
  public static String getGrade(String student, String module, String assessment,
      Map<String, Map<String, Map<String, String>>> map) {

    // Newly implemented getModule with Lambda Expression
    Transformer<Map<String, Map<String, String>>, Maybe<Map<String, String>>> 
        getModule = std -> Maybe.of(std.get(module));

    // Newly implemented getAssessment with Lambda Expression
    Transformer<Map<String, String>, Maybe<String>>
        getAssessment = mod -> Maybe.of(mod.get(assessment));

    // String to print
    String str = "No such entry";

    return Maybe.of(map.get(student))
                  .flatMap(getModule)
                    .flatMap(getAssessment)
                      .orElse(str);
  }

  public static void main(String[] args) {
    Map<String, Map<String, Map<String, String>>> students =
        Map.of(
          "Steve", Map.of(
            "CS2030S", Map.of(
              "lab1", "A",
              "lab2", "A-",
              "lab3", "A+",
              "lab4", "B",
              "pe1", "C"),
            "CS2040S", Map.of(
              "lab1", "A",
              "lab2", "A+",
              "lab3", "A+",
              "lab4", "A",
              "midterm", "A+")),
          "Tony", Map.of(
            "CS2030S", Map.of(
              "lab1", "C",
              "lab2", "C",
              "lab3", "B-",
              "lab4", "B+",
              "pe1", "A")));

    System.out.println(getGrade("Steve", "CS2030S", "lab1", students));
    System.out.println(getGrade("Steve", "CS2030S", "lab2", students));
    System.out.println(getGrade("Steve", "CS2040S", "lab3", students));
    System.out.println(getGrade("Steve", "CS2040S", "lab4", students));
    System.out.println(getGrade("Tony", "CS2030S", "lab1", students));
    System.out.println(getGrade("Tony", "CS2030S", "midterm", students));
    System.out.println(getGrade("Tony", "CS2040S", "lab4", students));
    System.out.println(getGrade("Bruce", "CS2040S", "lab4", students));
  }
}
