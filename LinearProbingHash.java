package DataStructureProject3;

public class LinearProbingHash<T> {

        public T[] table;
        public int M;
        public int N;

        public LinearProbingHash(int M) {
            table = (T[]) new Object[M];
            this.M = M;
        }

        public int hash(T t) {
            return ((t.hashCode() & 0x7fffffff) % M);
        }

        public void resize() {
            T[] oldTable = table;
            table = (T[]) new Object[M*2];
            M = M*2;
            for (int i = 0; i < oldTable.length; i++) {
                table[i] = oldTable[i];
            }
        }

        public T findElementByHashCode(int keyCode) {
            return table[keyCode];
        }

        public int findIndexOfElement(T key) {
            int index = hash(key);

            while (table[index] != null) {
                if (table[index].equals(key)) {
                    return index;
                }
                index = (index + 1) % M;
            }

            return -1;
        }

        public int insert(T key) {
            int hash = hash(key);

            if (N>=M/2) {
                resize();
            }

            while (table[hash] != null) {
                if (table[hash].equals(key)) {
                    return hash;
                }
                hash = (hash + 1) % M;
            }

            table[hash] = key;
            N++;
            return hash;
        }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < M; i++) {
            result += table[i] + ",";
        }
        return result + "]";
    }
}
