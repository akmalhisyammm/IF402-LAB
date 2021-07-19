package tutorial;

public class CountingEmployeeAddedListener implements EmployeeAddedListener {
    private static int employeesAddedCount = 0;

    @Override
    public void onEmployeeAdded(Employee employee) {
        // Increment jumlah karyawan
        employeesAddedCount++;

        // Print jumlah karyawan
        System.out.println("Total karyawan : " + employeesAddedCount);
    }
}
