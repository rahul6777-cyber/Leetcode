
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        
        long both = (long) Math.max(need1, need2) * costBoth;

        long separate = (long) need1 * cost1 + (long) need2 * cost2;

        long mixed = (long) Math.min(need1, need2) * costBoth
                   + (long) (need1 - Math.min(need1, need2)) * cost1
                   + (long) (need2 - Math.min(need1, need2)) * cost2;

        return Math.min(both, Math.min(separate, mixed));
    }
}
