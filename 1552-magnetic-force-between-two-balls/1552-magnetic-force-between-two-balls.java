class Solution {
    static boolean isValidAns(int[] position,int m,int mid){
        int ballCount=1;
        int lastposition=0;
        for(int i=1;i<position.length;i++){
            //current ballposition - lastballposition kar rahe hai 
            //agar wo miniDistance se bada hoga to naye ball ko place karenge
            if(position[i] - position[lastposition] >= mid){
//naye ball ko placed kar rahe hain
                ballCount++;
                //lastposition ko update kar rahe hain
                lastposition = i;

                if(ballCount==m){
                    //agar sab ball placed ho chuke hain to
                    return true;
                }
            }
        }
        return false;
    }
    public int maxDistance(int[] position, int m) {
       Arrays.sort(position);
        int n= position.length;
        int s=1;
        int e= position[n-1] -position[0];
        int ans=-1;

        while(s<=e){
            int mid=s+(e-s)/2;

            if(isValidAns(position,m,mid)){
                ans=mid;
                s=mid+1;
            }
            else{
                e=mid-1;
            }
        }
        return ans;
    }
}