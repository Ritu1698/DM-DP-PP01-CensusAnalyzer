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
    List<IndiaStateCodeCSV> stateCSVList = null;

    public static final String SORTED_INDIAN_STATE_CENSUS_DATA_JSON = "./src/test/resources/SortedIndiaStateCensusDataJsonFormat.json";
    public static final String SORTED_INDIAN_STATE_CODE_DATA_JSON = "./src/test/resources/SortedIndiaStateCodeDataJsonFormat.json";

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        checkFilePath(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            censusCSVList = CSVFactoryBuilder.createCSVBuilder().getCSVList(reader, IndiaCensusCSV.class);
            return censusCSVList.size();
        } catch (IOException | CSVException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.HEADER_DELIMITER_PROBLEM);
        }
    }

    public int loadIndianCodeData(String csvFilePath) throws CensusAnalyserException {
        checkFilePath(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            stateCSVList = CSVFactoryBuilder.createCSVBuilder().getCSVList(reader, IndiaStateCodeCSV.class);
            return stateCSVList.size();
        } catch (IOException | CSVException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.HEADER_DELIMITER_PROBLEM);
        }

    }

    public int loadIndiaCensusDataCommonsCSV(String csvFilePath) throws CensusAnalyserException {
        checkFilePath(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            List censusCSVCommonsList = CSVFactoryBuilder.createCommonsCSVBuilder().getCSVList(reader, IndiaCensusCSV.class);
            return censusCSVCommonsList.size();
        } catch (IOException | CSVException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.HEADER_DELIMITER_PROBLEM);
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

    public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
        try (Writer writer1 = new FileWriter(SORTED_INDIAN_STATE_CODE_DATA_JSON)) {
            if (stateCSVList== null || stateCSVList.size() == 0) {
                throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }
            Comparator<IndiaStateCodeCSV> censusComparator = Comparator.comparing(state -> state.stateCode);
            this.sortState(censusComparator);
            String json = new Gson().toJson(stateCSVList);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(stateCSVList, writer1);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.HEADER_DELIMITER_PROBLEM);
        }
    }

    public void sortState(Comparator<IndiaStateCodeCSV> censusComparator) {
        for (int i = 0; i < stateCSVList.size() - 1; i++) {
            for (int j = 0; j < stateCSVList.size() - i - 1; j++) {
                IndiaStateCodeCSV code1 = stateCSVList.get(j);
                IndiaStateCodeCSV code2 = stateCSVList.get(j + 1);
                if (censusComparator.compare(code1, code2) > 0) {
                    stateCSVList.set(j, code2);
                    stateCSVList.set(j + 1, code1);
                }
            }
        }
    }
}
