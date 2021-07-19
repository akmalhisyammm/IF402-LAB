package tugas;

public class Credit extends Payment {
    private int installment, maxInstallmentAmount;

    public Credit(Item item, int maxInstallmentAmount) {
        super(item);
        this.maxInstallmentAmount = maxInstallmentAmount;
        this.installment = 0;
    }

    @Override
    public int pay() {
        return item.getPrice() / maxInstallmentAmount;
    }

    @Override
    public int getRemainingAmount() {
        return (isPaidOff) ? 0 : item.getPrice() - (item.getPrice() * installment / maxInstallmentAmount);
    }

    @Override
    public String getClassName() {
        return "CREDIT";
    }

    @Override
    public void setPaidOff() {
        installment++;
        if(installment == maxInstallmentAmount) {
            super.setPaidOff();
        }
    }
}
