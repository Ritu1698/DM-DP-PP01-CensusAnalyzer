package censusanalyser;

import com.bridgelabz.csvbuilder.CSVException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            Iterator<IndiaCensusCSV> censusCSVIterator = CSVFactoryBuilder.createCSVBuilder().getCSVFileIterator(reader, IndiaCensusCSV.class);
            return this.getCount(censusCSVIterator);
        } catch (IOException | RuntimeException e) {
            throw new CSVException(e.getMessage(),
                    CSVException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }

    public int loadIndianCodeData(String csvFilePath) throws CSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            Iterator<IndiaStateCodeCSV> censusCSVIterator = CSVFactoryBuilder.createCSVBuilder().getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            return this.getCount(censusCSVIterator);
        } catch (IOException | RuntimeException e) {
            throw new CSVException(e.getMessage(),
                    CSVException.ExceptionType.CSV_FILE_PROBLEM);
        }

    }

    public <E> int getCount(Iterator<E> csvIterator) {
        Iterable<E> csvIterable = () -> csvIterator;
        return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
    }
}
