package be.qbmt.dv.devstore;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.net.URI;

import be.qbmt.dv.devstore.db.DataManager;
import be.qbmt.dv.devstore.db.DbHelper;
import be.qbmt.dv.devstore.db.DevStoreDbContract;

/**
 * Created by dv on 28/12/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    public static final String TAG = ProductAdapter.class.getSimpleName();

    private DbHelper helper;
    private DataManager manager;
    private Cursor productCursor;
    private LayoutInflater layoutInflater;
    private ProductAdapter self;

    private StoreFragmentListener listener;

    private Context context;

    public ProductAdapter(Context context) {
        this.context = context;
        self = this;
        helper = new DbHelper(context);
        manager = new DataManager(helper);
        productCursor = manager.getProductsCursor();
        layoutInflater = LayoutInflater.from(context);
        listener = (StoreFragmentListener)context;  //Gevaarlijk!

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.store_item,parent,false);

        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        productCursor.moveToPosition(position);
        DevStoreDbContract.Product thisProduct = DevStoreDbContract.ProductEntry.constructFromCursor(productCursor);
        holder.setProduct(thisProduct);
    }

    @Override
    public int getItemCount() {
        return productCursor.getCount();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView titleView, descriptionView, priceView;
        private ImageView imageView;

        private DevStoreDbContract.Product product;

        public ProductViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.itemtitle);
            imageView = itemView.findViewById(R.id.itemproductimage);
            descriptionView = itemView.findViewById(R.id.itemdescription);
            priceView = itemView.findViewById(R.id.itemprice);

            View.OnClickListener selectItem = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click();
                }
            };

            titleView.setOnClickListener(selectItem);
            imageView.setOnClickListener(selectItem);
            descriptionView.setOnClickListener(selectItem);
            priceView.setOnClickListener(selectItem);

        }

        protected void click() {
            ProductAdapter.this.listener.storeItemTouched(this);
        }

        protected void setProduct(DevStoreDbContract.Product p) {
            product = p;
            titleView.setText(p.name);
            descriptionView.setText(p.description);
            priceView.setText(Util.priceConvert(p.price));

            String fileUrl = "file:///android_asset/" + p.imageResource;
            Log.d(TAG, "setProduct: fileUrl:" + fileUrl);

            Uri imageUri = Uri.parse("file:///android_asset/" + p.imageResource);

            Glide.with(ProductAdapter.this.context).asBitmap().load(imageUri).into(imageView);


        }

        public DevStoreDbContract.Product getProduct() {
            return product;
        }



    }
}
