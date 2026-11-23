class ProductOfNumbers {
    int[] prefix = new int[40001];
    int size = 0;

    public ProductOfNumbers() {
        prefix[0] = 1;
    }

    public void add(int num) {
        if (num == 0) {
            size = 0;
            prefix[0] = 1;
        } else {
            prefix[++size] = prefix[size - 1] * num;
        }
    }

    public int getProduct(int k) {
        if (k > size) return 0;
        return prefix[size] / prefix[size - k];
    }
}