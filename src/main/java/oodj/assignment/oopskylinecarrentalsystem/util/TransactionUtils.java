package oodj.assignment.oopskylinecarrentalsystem.util;

import oodj.assignment.oopskylinecarrentalsystem.model.Transaction;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransactionUtils {
    private static final String transactionFilePath;
    private static final List<Transaction> transactionList;

    static {
        transactionList = new ArrayList<>();

        String transactionFilePathRegex = "(?<=IdeaProjects/oop-skyline-car-rental-system/)(target/classes)(?=/oodj/assignment/oopskylinecarrentalsystem/textfiles/Transaction\\.txt$)";
        Pattern transactionFilePathPattern = Pattern.compile(transactionFilePathRegex);
        Matcher transactionFilePathMatcher = transactionFilePathPattern.matcher(Objects.requireNonNull(TransactionUtils.class.getResource("/oodj/assignment/oopskylinecarrentalsystem/textfiles/Transaction.txt")).getPath());
        String pathReplacement = "src/main/resources";

        String incompleteUserFilePath = transactionFilePathMatcher.replaceFirst(pathReplacement);

        transactionFilePath = URLDecoder.decode((incompleteUserFilePath.substring(1)), StandardCharsets.UTF_8);

        try {
            List<String> transactionDatabase = new ArrayList<>(FileUtils.readLines(new File(transactionFilePath), Charset.defaultCharset()));

            for(String transaction: transactionDatabase) {
                String[] transactionData = transaction.split(" \\|\\| ");
                transactionList.add(new Transaction(transactionData));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Transaction> getTransactionList() {
        return transactionList;
    }

    public static void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    public static void updateFile() {
        List<String> transactionDatabase = new ArrayList<>();

        for(Transaction transaction: transactionList) {
            transactionDatabase.add(transaction.fileFormat());
        }

        try {
            FileUtils.writeLines(new File(transactionFilePath), transactionDatabase);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
