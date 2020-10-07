package com.svetaukiyo.transactionAnalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Analyser {

    public static void main(String[] args) throws ParseException {
        List<Transaction> transactions = readFromCSV();
        SimpleDateFormat formatter = new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");
        int numberOfTransaction = 0;
        double averageTransactionValue = 0.0;

        Scanner in = new Scanner(System.in);
        System.out.print("fromDate: ");
        Date fromDate = formatter.parse(in.nextLine());
        System.out.print("toDate: ");
        Date toDate = formatter.parse(in.nextLine());
        System.out.print("merchant: ");
        String merchant = in.nextLine();
        in.close();
        for (Transaction t : transactions) {
            if (t.getMerchant().equals(merchant)) {
                if (t.getType().equals(Type.PAYMENT)) {
                    if (t.getDate().after(fromDate) && t.getDate().before(toDate)) {
                        numberOfTransaction++;
                        averageTransactionValue += t.getAmount();
                    }
                }
            }
        }
        System.out.println(averageTransactionValue/numberOfTransaction);
        System.out.println(numberOfTransaction);
    }

    private static Transaction createTransaction(String[] metadata) throws ParseException {
        String id = metadata[0];
        SimpleDateFormat formatter = new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");
        Date date = formatter.parse(metadata[1].trim());
        double amount = Double.parseDouble(metadata[2].trim());
        String merchant = metadata[3].trim();
        Type type = Type.valueOf(metadata[4].trim());
        return new Transaction(id, date, amount, merchant, type);
    }

    private static List<Transaction> readFromCSV() {
        List<Transaction> transactions = new ArrayList<>();
        String csvFile = "src/resources/csv.csv";
        int skipLine = 1;
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            skipLine(br, skipLine);
            String line = br.readLine();
            while (line != null) {
                String[] metadata = line.split(",");
                Transaction transaction = createTransaction(metadata);
                transactions.add(transaction);
                line = br.readLine();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    private static void skipLine(BufferedReader reader, int skipLine) throws IOException {
        for(int i =0; i<skipLine; i++)
            reader.readLine();
    }
}
