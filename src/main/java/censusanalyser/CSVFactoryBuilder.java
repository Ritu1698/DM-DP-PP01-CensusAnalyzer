package censusanalyser;

import com.bridgelabz.csvbuilder.CommonsCSVBuilder;
import com.bridgelabz.csvbuilder.ICSVBuilder;
import com.bridgelabz.csvbuilder.OpenCSVBuilder;

public class CSVFactoryBuilder {
    public static ICSVBuilder createCSVBuilder() { return new OpenCSVBuilder(); }

    public static ICSVBuilder createCommonsCSVBuilder() { return new CommonsCSVBuilder();
    }
}
