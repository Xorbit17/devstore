package be.qbmt.dv.devstore;

import be.qbmt.dv.devstore.db.DevStoreDbContract;

/**
 * Created by dv on 28/12/2017.
 */

public interface StoreFragmentListener {
    public void storeItemTouched(ProductAdapter.ProductViewHolder vh);
}
