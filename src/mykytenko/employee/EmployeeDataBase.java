package mykytenko.employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDataBase {
    private final Map<Integer, Employee> employees = new HashMap<>();

    private int id = 10000;

    public int add(Employee e){
        int i = generateId();
        employees.put(i, e);
        return i;
    }

    public Employee getById(int id){
        return employees.get(id);
    }

    private int generateId(){
        return id++;
    }
}
