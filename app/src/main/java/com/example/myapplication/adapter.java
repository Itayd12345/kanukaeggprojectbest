package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder>{
     //   הנתונים ממסד שנקבל השחקנים רשימת //
        private ArrayList<Player> playersList;
      //  בונה פעולה //
        public adapter(ArrayList<Player> playersList) {
            this.playersList = playersList;
        }
        //בודדת שורה של התצוגה לרכיבי הקישורים את ששומרת פעולה //
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
                viewType) {
           // Java לאובייקט בודדת שורה של XML-ה קובץ את הופכים אנחנו כאן //
             //       (עכשיו אותו שנוסיף או) xml.item_row בשם layout קובץ לך שיש ודאי //
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_item, parent, false);
            return new MyViewHolder(view);
        }
      //  שלו הנתונים את ומציג שהתקבל המיקום לפי שחקן שולף //
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     //       המסך על אותו ומציגים מהרשימה נתון לוקחים :הקסם קורה כאן //
            Player currentUser = playersList.get(position);
            holder.tvName.setText(currentUser.getName());
            holder.tvScore.setText(String.valueOf(currentUser.getGames()));
        }
//:ברשימה השורות מספר את מחזיר //
        @Override
        public int getItemCount() {
            return playersList.size();
        }
 //       בודדת שורה של התצוגה לרכיבי הקישור את שומר //
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tvName, tvScore;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tvName = itemView.findViewById(R.id.tv_row_name);
                tvScore = itemView.findViewById(R.id.tv_row_score);
            }
        }
}
