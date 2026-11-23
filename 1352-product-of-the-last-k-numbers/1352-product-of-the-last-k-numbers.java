class ProductOfNumbers {
    private List<Integer> prefix;
    private int prod;

    public ProductOfNumbers() {
        prefix = new ArrayList<>();
        prefix.add(1);
        prod = 1;
    }

    public void add(int num) {
        if (num == 0) {
            prefix = new ArrayList<>();
            prefix.add(1);
            prod = 1;
        } else {
            prod *= num;
            prefix.add(prod);
        }
    }

    public int getProduct(int k) {
        int n = prefix.size();
        if (k >= n) return 0;
        return prefix.get(n - 1) / prefix.get(n - k - 1);
    }
}