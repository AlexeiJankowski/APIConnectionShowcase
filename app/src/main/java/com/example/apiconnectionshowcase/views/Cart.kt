package com.example.apiconnectionshowcase.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.apiconnectionshowcase.data.Product
import com.example.apiconnectionshowcase.viewmodel.ProductViewModel
import com.example.apiconnectionshowcase.viewmodel.UserViewModel

@Composable
fun Cart(
    modifier: Modifier,
    productViewModel: ProductViewModel,
    userViewModel: UserViewModel,
    onProductClicked: () -> Unit
) {
    productViewModel.getProducts()
    val productsUIState by productViewModel.productsUIState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        userViewModel.getCart()
    }
    val cart by userViewModel.cart

    LazyColumn(
        modifier = modifier
    ) {
        cart?.products?.let { cartProducts ->
            items(cartProducts) { cartProduct ->
                Log.d("ProductID", cartProduct.productId.toString())
                // Get product details based on cart's product ID
                val product = productsUIState.products.find { it.id == cartProduct.productId }
                if (product != null) {
                    Log.d("ProductIN", product.title)
                    CartItem(
                        productViewModel = productViewModel,
                        onProductClicked = onProductClicked,
                        product = product
                    )
                }
            }
        } ?: item {
            Text("No items in the cart", modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun CartItem(productViewModel: ProductViewModel,
             onProductClicked: () -> Unit,
             product: Product
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable {
                productViewModel.getProduct(product.id)
                onProductClicked()
            },
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .weight(4f)
                    .height(120.dp)
                    .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(7f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = "$${product.price}",
                    fontSize = 16.sp,
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}