package com.example.apiconnectionshowcase.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.apiconnectionshowcase.data.Product
import com.example.apiconnectionshowcase.viewmodel.ProductViewModel

@Composable
fun ProductDetails(
    modifier: Modifier,
    productViewModel: ProductViewModel
) {
    val productUIState by productViewModel.productUIState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (productUIState.isLoading) {
            Text(text = "Loading...")
        } else if (productUIState.selectedProduct != null) {
            ProductInfo(
                product = productUIState.selectedProduct!!,
                productViewModel = productViewModel
            )
        } else if (productUIState.error != null) {
            Text(text = "Error: ${productUIState.error}")
        } else {
            Text(text = "Nothing to show")
        }
    }
}

@Composable
fun ProductInfo(
    product: Product,
    productViewModel: ProductViewModel
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

//    Make it the part of a Cart class (which I need to create)
    val productCount = remember { mutableIntStateOf(1) }

    Column(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight / 3),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = product.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Category: ${product.category.capitalize()}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = product.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            repeat(5) { index ->
                val starIcon = if (index < product.rating.rate.toInt()) {
                    Icons.Filled.Star
                } else {
                    Icons.Outlined.Star
                }
                Icon(
                    imageVector = starIcon,
                    contentDescription = "Rating star",
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = "(${product.rating.count} reviews)",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Price section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Price: $${product.price}",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = {
                    if (productCount.intValue > 1) {
                        productCount.intValue -= 1
                    }
                }) {
                    Text(text = "-")
                }
                Text(
                    text = productCount.intValue.toString(),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Button(onClick = {
                    productCount.intValue += 1
                }) {
                    Text(text = "+")
                }
            }
        }

        if (product.price * productCount.intValue != product.price) {
            Text(
                text = "Total: $${product.price * productCount.intValue}",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
        }
    }

    Column {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                productViewModel.addToCart(product)
            }
        ) {
            Text(text = "Add to Cart")
        }
    }
}