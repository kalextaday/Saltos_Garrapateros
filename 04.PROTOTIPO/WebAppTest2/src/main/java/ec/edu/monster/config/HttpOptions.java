package ec.edu.monster.config;

/**
 *
 * @author guffenix
 */
public enum HttpOptions {

    ENCABEZADO_UTF8("text/html;charset=UTF-8", "", "", "", ""),
    ENCABEZADO_HTPP_1_1("Cache-Control", "no-cache, no-store, must-revalidate", "", "", ""),
    ENCABEZADO_HTPP_1_0("Pragma", "no-cache", "", "", "");

    private final String option1;
    private final String option2;
    private final String option3;
    private final String option4;
    private final String option5;

    HttpOptions(String option1, String option2, String option3, String option4, String option5) {
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getOption5() {
        return option5;
    }

}
