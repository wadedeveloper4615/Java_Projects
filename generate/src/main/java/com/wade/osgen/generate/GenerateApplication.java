package com.wade.osgen.generate;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wade.osgen.generate.grammar.Java9Lexer;
import com.wade.osgen.generate.grammar.Java9Parser;

@SpringBootApplication
public class GenerateApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(GenerateApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    public void parseFile(String f) {
        try {
            Lexer lexer = new Java9Lexer(CharStreams.fromFileName(f));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            Java9Parser parser = new Java9Parser(tokens);
            parser.addErrorListener(new DiagnosticErrorListener());
            parser.setErrorHandler(new BailErrorStrategy());
            parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
            ParserRuleContext t = parser.compilationUnit();
            parser.setBuildParseTree(false);
            System.out.println(t.toStringTree(parser));
        } catch (Exception e) {
            System.err.println("parser exception: " + e);
            e.printStackTrace();   // so we can get stack trace
        }
    }
}
