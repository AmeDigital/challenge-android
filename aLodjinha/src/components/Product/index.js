import React from 'react';
import { View, Image, Text, TouchableOpacity } from 'react-native';

import styles from './styles';

export default function Product({ navigation }) {
	return (
		<View style={styles.produto}>
			<Image
				style={styles.image}
				source={{
					uri: 'https://images-submarino.b2w.io/produtos/01/00/item/128926/4/128926443_1GG.png'
				}}
			/>
			<View style={styles.containerDescription}>
				<Text style={styles.textDescription} numberOfLines={2}>
					Mussum Ipsum, cacilds vidis litro abertis. Mussum Ipsum, cacilds vidis litro abertis. Mussum Ipsum,
					cacilds vidis litro abertis.
				</Text>
				<View style={styles.containerPrices}>
					<Text style={styles.priceOf}>De: 299</Text>
					<Text style={styles.priceBy}>Por 119,99</Text>
				</View>
			</View>
			<View style={styles.containerImageRow}>
				<TouchableOpacity onPress={() => navigation.navigate('DescriptionProduct')}>
					<Image style={styles.imageRow} source={require('../../images/disclosure_indicator.png')} />
				</TouchableOpacity>
			</View>
		</View>
	);
}
