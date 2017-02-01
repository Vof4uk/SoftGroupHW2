package mykytenko.employee;

import java.util.*;

public class EmployeesBonus {

    //ArrayList because firstName and lastName can be not unique
    //Any time can be replaced with any collection
    private Collection<Employee> employees = new ArrayList<>();

    public void addEmployee(String fistName, String lastNAme){
        employees.add(new Employee(fistName, lastNAme));
    }

    public String chooseEmployeeForBonus(){
        Employee lucky = chooseRandomEmployee();
        return lucky == null ? null : lucky.fullName();
    }

    private Employee chooseRandomEmployee(){
        Random rnd = new Random(System.currentTimeMillis());
        int count = Math.abs(rnd.nextInt() % employees.size());
        for (Employee e : employees) {
            if(--count < 0){
                return e;
            }
        }
        return null;
    }

    public List<String> getEmployeesList(){
        List<String> resultList = new ArrayList<>();
        for (Employee e: employees) {
            resultList.add(e.fullName());
        }
        return resultList;
    }
}
