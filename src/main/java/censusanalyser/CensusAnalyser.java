package censusanalyser;

import com.bridgelabz.csvbuilder.CSVException;
import com.bridgelabz.csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        checkFilePath(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            List censusCSVList = CSVFactoryBuilder.createCSVBuilder().getCSVList(reader, IndiaCensusCSV.class);
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
            List censusCSVList = CSVFactoryBuilder.createCSVBuilder().getCSVList(reader, IndiaCensusCSV.class);
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

    private void checkFilePath(String csvFilePath) throws CensusAnalyserException {
        Pattern patternForCSV = Pattern.compile(".+[.csv]");
        if (!patternForCSV.matcher(csvFilePath).matches())
            throw new CensusAnalyserException("Wrong File",
                    CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM);
    }

    public <E> int getCount(Iterator<E> csvIterator) {
        Iterable<E> csvIterable = () -> csvIterator;
        return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
    }
}
