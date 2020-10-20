package censusanalyser;

public class CSVFactoryBuilder {
    public static ICSVBuilder createCSVBuilder(){
        return (ICSVBuilder) new OpenCSVBuilder();
    }
}
