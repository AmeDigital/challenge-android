import React from 'react';
import { Text, View, StatusBar, Image } from 'react-native';

import styles from './styles';

function DescriptionProductScreen() {
	return (
		<View style={styles.productDescription}>
			<StatusBar backgroundColor="#aaaaaa" />
			<Image
				source={{
					uri: 'https://images-submarino.b2w.io/produtos/01/00/item/128926/4/128926443_1GG.png'
				}}
				style={styles.productImage}
			/>
			<Text style={styles.nameDescription}>Nome do produto com uma descrição grandona</Text>
			<View style={styles.containerPrices}>
				<Text style={styles.priceOf}>De: 299</Text>
				<Text style={styles.priceBy}>Por 119,99</Text>
			</View>
			<View style={styles.containerDescription}>
				<Text style={styles.title}>Titulo</Text>
				<Text style={styles.description}>
					Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou
					faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat
					hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis. Copo furadis
					é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula
					non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é
					amistosis quis leo. A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum
					egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito
					paradis? Toma um mé que o mundo vai girarzis!
				</Text>
			</View>
		</View>
	);
}

DescriptionProductScreen.navigationOptions = {
	headerStyle: {
		backgroundColor: '#fff',
		marginTop: StatusBar.currentHeight,
		elevation: 0
	},
	headerTintColor: '#333'
};

export default DescriptionProductScreen;
