package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.entities.Employee;


public class Program {

    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);

        String path = "C:\\teste\\in.txt";
        
        List<Employee> employees = new ArrayList<>();
       
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();

            while (line != null) {
                String[] vetor = line.split(",");
                String name = vetor[0];
                String email = vetor[1];
                Double salary = Double.parseDouble(vetor[2]);
                
                Employee employee = new Employee(name,email,salary);
                employees.add(employee);
                
                line = br.readLine();
            }
            
            employees.forEach(System.out::println); // Printando todos os funcionarios
            
            System.out.print("\nInforme o salario: ");
            Double salaryUp = sc.nextDouble();
            System.out.print("Email dos funcionarios que ganham mais que R$" + String.format("%.2f", salaryUp) + ": \n");
            
            //Pipeline
            List<String> newListEmployee = employees.stream() // Transforma a lista em uma stream
            		.filter(x -> x.getSalary() > salaryUp) // Filtra apenas os funcinarios que ganham mais que salaryUp
            		.map(x -> x.getEmail()) // Troca o conteudo da lista de funcionario para apenas os emails dos funcionarios
            		.sorted() // Colocando em ordem alfabetica        		
            		.collect(Collectors.toList()); // Transforma a stream novamente em uma lista
            
            newListEmployee.forEach(System.out::println);
            
           //Pipeline
            Double sum = employees.stream()
            		.filter(x -> x.getName().charAt(0) == 'M') // Filtra apenas os funcionarios cujo a primera letra do nome é M
            		.map(x -> x.getSalary()) // Troca o conteudo da lista de funcinario para apenas os salarios dos funcionarios
            		.reduce(0.0, (x,y) -> x + y); // Soma os salarios
            System.out.println("\nSoma do salario dos funcionarios cujo a primeira letra do nome é 'M': " + String.format("R$%.2f", sum)); // Printa o resultado do pipeline
           
            		
            		
        }
        catch(IOException e) {
        	System.out.println("Error: " + e.getMessage());
        }
        
     
        

        sc.close();
    }


}
