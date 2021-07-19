package tugas;

public class Cash extends Payment {
    public Cash(Item item) {
        super(item);
    }

    @Override
    public int pay() {
        if(isPaidOff) {
            return 0;
        }
        return this.item.getPrice();
    }

    @Override
    public String getClassName() {
        return "CASH";
    }

    @Override
    public void setPaidOff() {
        super.setPaidOff();
    }
}
