package com.sltc.soa.client;

import com.sltc.soa.client.stub.CurrencyConversionWs;
import com.sltc.soa.client.stub.CurrencyConversionWsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {

    JButton convertButton, clearButton;
    JComboBox sourceComboBox, targetComboBox;
    JFormattedTextField sourceTextField, targetTextField;

    CurrencyConversionWs currencyConversionWs;

    public GUI() {

        CurrencyConversionWsService currencyConversionWsService = new CurrencyConversionWsService();
        currencyConversionWs = currencyConversionWsService.getCurrencyConversionWsPort();

        String[] currencyCode = {"FJD", "MXN", "STD", "SCR", "CDF", "BBD", "GTQ", "CLP", "HNL", "UGX", "ZAR", "TND", "STN",
                "CUC", "BSD", "SLL", "SDG", "IQD", "CUP", "GMD", "TWD", "RSD", "DOP", "KMF", "MYR", "FKP", "XOF",
                "GEL", "BTC", "UYU", "MAD", "CVE", "TOP", "AZN", "OMR", "PGK", "KES", "SEK", "CNH", "BTN", "UAH",
                "GNF", "ERN", "MZN", "SVC", "ARS", "QAR", "IRR", "MRO", "XPD", "CNY", "THB", "UZS", "XPF", "MRU",
                "BDT", "LYD", "BMD", "KWD", "PHP", "XPT", "RUB", "PYG", "ISK", "JMD", "COP", "MKD", "USD", "DZD",
                "PAB", "GGP", "SGD", "ETB", "JEP", "KGS", "SOS", "VEF", "VUV", "LAK", "BND", "XAF", "LRD", "XAG",
                "CHF", "HRK", "ALL", "DJF", "VES", "ZMW", "TZS", "VND", "XAU", "AUD", "ILS", "GHS", "GYD", "KPW",
                "BOB", "KHR", "MDL", "IDR", "KYD", "AMD", "BWP", "SHP", "TRY", "LBP", "TJS", "JOD", "AED", "HKD",
                "RWF", "EUR", "LSL", "DKK", "CAD", "BGN", "MMK", "MUR", "NOK", "SYP", "IMP", "ZWL", "GIP", "RON",
                "LKR", "NGN", "CRC", "CZK", "PKR", "XCD", "ANG", "HTG", "BHD", "KZT", "SRD", "SZL", "SAR", "TTD",
                "YER", "MVR", "AFN", "INR", "AWG", "KRW", "NPR", "JPY", "MNT", "AOA", "PLN", "GBP", "SBD", "BYN",
                "HUF", "BIF", "MWK", "MGA", "XDR", "BZD", "BAM", "EGP", "MOP", "NAD", "SSP", "NIO", "PEN", "NZD",
                "WST", "TMT", "CLF", "BRL"};

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 100));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));


        sourceComboBox = new JComboBox<>(currencyCode);
        targetComboBox = new JComboBox<>(currencyCode);

        sourceTextField = new JFormattedTextField(0.0);
        sourceTextField.setPreferredSize(new Dimension(100, 27));
        sourceTextField.setHorizontalAlignment(JFormattedTextField.RIGHT);


        targetTextField = new JFormattedTextField(0.0);
        targetTextField.setPreferredSize(new Dimension(100, 27));
        targetTextField.setHorizontalAlignment(JFormattedTextField.RIGHT);
        targetTextField.setEditable(false);


        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLayout(new GridLayout(2, 1));


        panel1.add(sourceTextField);
        panel1.add(sourceComboBox);

        panel2.add(targetTextField);
        panel2.add(targetComboBox);

        topPanel.add(panel1);
        topPanel.add(panel2);

        bottomPanel.add(convertButton);

        this.add(topPanel);
        this.add(bottomPanel);
        this.setTitle("Currency Convertor");
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {

            String sourceCurrency = sourceComboBox.getSelectedItem().toString();
            double sourceCurrencyAmount = (double) sourceTextField.getValue();
            String targetCurrency = targetComboBox.getSelectedItem().toString();

            double targetCurrencyAmount = currencyConversionWs.convert(sourceCurrencyAmount, sourceCurrency, targetCurrency);

            targetTextField.setValue(targetCurrencyAmount);
        }
    }


}
