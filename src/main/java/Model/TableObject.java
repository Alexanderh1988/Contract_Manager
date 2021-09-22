package Model;

public class TableObject {

    String Id;
    String Name;
    String Salary;

    public  TableObject(String  Id,String Name, String Salary){
        super();
        this.Id=Id;
        this.Name=Name;
        this.Salary=Salary;
    }

    public String  getId() {
        return Id;
    }

    public void setId(String  id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }
}
