package censusanalyser;

import com.bridgelabz.csvbuilder.CSVException;
import com.bridgelabz.csvbuilder.ICSVBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    List<IndiaCensusCSV> censusCSVList = null;
    public static final  String SORTED_INDIAN_STATE_CENSUS_DATA_JSON = "./src/test/resources/SortedIndiaStateCensusDataJsonFormat.json";
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        checkFilePath(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            censusCSVList = CSVFactoryBuilder.createCSVBuilder().getCSVList(reader, IndiaCensusCSV.class);
            return censusCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.HEADER_DELIMITER_PROBLEM);
        } catch (CSVException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }
    public int loadIndianCodeData(String csvFilePath) throws CensusAnalyserException {
        checkFilePath(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            censusCSVList = CSVFactoryBuilder.createCSVBuilder().getCSVList(reader, IndiaCensusCSV.class);
            return censusCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.HEADER_DELIMITER_PROBLEM);
        } catch (CSVException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }

    }
    public int loadIndiaCensusDataCommonsCSV(String csvFilePath) throws CensusAnalyserException {
        checkFilePath(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            List censusCSVCommonsList = CSVFactoryBuilder.createCommonsCSVBuilder().getCSVList(reader, IndiaCensusCSV.class);
            return censusCSVCommonsList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.HEADER_DELIMITER_PROBLEM);
        } catch (CSVException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }
    public void checkFilePath(String csvFilePath) throws CensusAnalyserException {
        Pattern patternForCSV = Pattern.compile(".+[.csv]");
        if (!patternForCSV.matcher(csvFilePath).matches())
            throw new CensusAnalyserException("Wrong File",
                    CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM);
    }
    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        try (Writer writer = new FileWriter(SORTED_INDIAN_STATE_CENSUS_DATA_JSON)) {
            if (censusCSVList == null || censusCSVList.size() == 0) {
                throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }
            Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
            this.sort(censusComparator);
            String json = new Gson().toJson(censusCSVList);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(censusCSVList, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.HEADER_DELIMITER_PROBLEM);
        }
    }
    public void sort(Comparator<IndiaCensusCSV> censusComparator) {
        int listSize = censusCSVList.size();
        IntStream.range(0, listSize - 1).flatMap(i -> IntStream.range(1, listSize - i)).forEach(j -> {
            IndiaCensusCSV census1 = censusCSVList.get(j - 1);
            IndiaCensusCSV census2 = censusCSVList.get(j);
            if (censusComparator.compare(census1, census2) > 0) {
                censusCSVList.set(j - 1, census2);
                censusCSVList.set(j, census1);
            }
        });
    }
}
