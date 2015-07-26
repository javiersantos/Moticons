package com.javiersantos.moticons.adapters;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.javiersantos.moticons.Moticon;
import com.javiersantos.moticons.MoticonsApplication;
import com.javiersantos.moticons.R;
import com.javiersantos.moticons.activities.MainActivity;
import com.javiersantos.moticons.activities.MoticoinsActivity;
import com.javiersantos.moticons.utils.AppPreferences;
import com.javiersantos.moticons.utils.UtilsDialog;
import com.javiersantos.moticons.utils.UtilsMoticons;
import com.javiersantos.moticons.utils.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class MoticonAdapter extends RecyclerView.Adapter<MoticonAdapter.MoticonViewHolder> implements Filterable {
    // MoticonAdapter variables
    private List<Moticon> moticonList;
    private List<Moticon> moticonListSearch;
    private Context context;
    private Activity activity;
    private AppPreferences appPreferences;

    public MoticonAdapter(List<Moticon> moticonList, Context context) {
        this.moticonList = moticonList;
        this.context = context;
        this.activity = (Activity) context;
        this.appPreferences = MoticonsApplication.getAppPreferences();
    }

    @Override
    public int getItemCount() {
        return moticonList.size();
    }

    public void clear() {
        moticonList.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MoticonViewHolder moticonViewHolder, int i) {
        final Moticon moticon = moticonList.get(i);
        moticonViewHolder.vMoticonName.setText(moticon.getMoticon());
        moticonViewHolder.vMoticonCategory.setText(UtilsUI.convertCategoryToString(context, moticon));

        Log.i("Unlocked?", moticon.getMoticon() + moticon.getUnlocked().toString());

        // Moticon is Unlocked
        if (moticon.getUnlocked()) {
            moticonViewHolder.vMoticonTimes.setText(moticon.getTimes().toString());
            moticonViewHolder.vMoticonTimes.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

            moticonViewHolder.vMoticonCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClipData clipData;

                    ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    clipData = ClipData.newPlainText("text", moticon.getMoticon());
                    clipboardManager.setPrimaryClip(clipData);
                    UtilsDialog.showSnackbar(activity, String.format(context.getResources().getString(R.string.copied_clipboard), moticon.getMoticon())).show();

                    Integer moticonTimes = UtilsMoticons.increaseMoticonTimes(moticon);
                    moticonViewHolder.vMoticonTimes.setText(moticonTimes.toString());
                }
            });

            moticonViewHolder.vMoticonCard.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, moticon.getMoticon());
                    context.startActivity(Intent.createChooser(intent, String.format(context.getResources().getString(R.string.action_share), moticon.getMoticon())));

                    Integer moticonTimes = UtilsMoticons.increaseMoticonTimes(moticon);
                    moticonViewHolder.vMoticonTimes.setText(moticonTimes.toString());
                    return false;
                }
            });
            // Moticon isn't Unlocked
        } else {
            moticonViewHolder.vMoticonTimes.setText(moticon.getMoticoins().toString());
            moticonViewHolder.vMoticonTimes.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_stars_white, 0);
            moticonViewHolder.vMoticonTimes.setCompoundDrawablePadding(4);

            moticonViewHolder.vMoticonCard.setOnLongClickListener(null);

            // User has enough Moticoins to buy the Moticon
            if (UtilsMoticons.canBuyMoticon(moticon)) {
                moticonViewHolder.vMoticonCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder confirmUnlock = UtilsDialog.showDialog(context, context.getResources().getString(R.string.moticon_unlock), String.format(context.getString(R.string.moticon_unlock_description), appPreferences.getMoticoins(), moticon.getMoticoins(), moticon.getMoticon()));
                        confirmUnlock.setPositiveButton(context.getResources().getString(R.string.button_unlock), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                UtilsMoticons.buyMoticon(context, moticon);
                                notifyDataSetChanged();
                                dialogInterface.dismiss();
                            }
                        });
                        confirmUnlock.setNegativeButton(context.getResources().getString(R.string.button_later), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        confirmUnlock.show();
                    }
                });
                // User hasn't enough Moticoins to buy the Moticon
            } else {
                moticonViewHolder.vMoticonCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder noMoticoinsDialog = UtilsDialog.showDialog(context, context.getResources().getString(R.string.moticon_no_moticoins), context.getString(R.string.moticon_no_moticoins_description));
                        noMoticoinsDialog.setPositiveButton(context.getResources().getString(R.string.button_moticoins_shop), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                context.startActivity(new Intent(context, MoticoinsActivity.class));
                                activity.finish();
                            }
                        });
                        noMoticoinsDialog.setNegativeButton(context.getResources().getString(R.string.button_later), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        noMoticoinsDialog.show();
                    }
                });
            }
        }

    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults oReturn = new FilterResults();
                final List<Moticon> results = new ArrayList<>();
                if (moticonListSearch == null) {
                    moticonListSearch = moticonList;
                }
                if (charSequence != null) {
                    if (moticonListSearch != null && moticonListSearch.size() > 0) {
                        for (final Moticon moticon : moticonListSearch) {
                            if (moticon.getMoticon().toLowerCase().contains(charSequence.toString()) || moticon.getKeywords().toString().toLowerCase().contains(charSequence.toString())) {
                                results.add(moticon);
                            }
                        }
                    }
                    oReturn.values = results;
                    oReturn.count = results.size();
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if (filterResults.count > 0) {
                    MainActivity.setResultsMessage(false);
                } else {
                    MainActivity.setResultsMessage(true);
                }
                moticonList = (ArrayList<Moticon>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    @Override
    public MoticonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View motionAdapterView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.moticon_layout, viewGroup, false);
        return new MoticonViewHolder(motionAdapterView);
    }

    public static class MoticonViewHolder extends RecyclerView.ViewHolder {
        protected TextView vMoticonName;
        protected TextView vMoticonTimes;
        protected TextView vMoticonCategory;
        protected CardView vMoticonCard;

        public MoticonViewHolder(View v) {
            super(v);
            vMoticonName = (TextView) v.findViewById(R.id.moticon_name);
            vMoticonTimes = (TextView) v.findViewById(R.id.moticon_times);
            vMoticonCategory = (TextView) v.findViewById(R.id.moticon_category);
            vMoticonCard = (CardView) v.findViewById(R.id.moticon_card);
        }
    }

}
