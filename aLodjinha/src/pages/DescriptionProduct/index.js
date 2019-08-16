import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { Text, View, StatusBar, Image, ScrollView, Alert } from 'react-native';

import api from '../../services/api';
import FloatingButton from '../../components/FloatingButton';
import { replacePonitForComma } from '../../utils/Utils';

import styles from './styles';
import Loading from '../../components/Loading';

function DescriptionProductScreen({ navigation }) {
	const id = navigation.getParam('id');
	const [ product, setProduct ] = useState(undefined);

	useEffect(
		() => {
			(async function loadProduct() {
				const response = await api.get(`/produto/${id}`);
				setProduct(response.data);
			})();
		},
		[ id ]
	);

	return (
		<View style={{ flex: 1 }}>
			{product !== undefined ? (
				<ScrollView
					onScroll={(event) => {
						if (event.nativeEvent.contentOffset.y > 0) {
							console.log(navigation);
						} else {
							console.log('Top');
						}
					}}
				>
					<View style={styles.productDescription}>
						<StatusBar backgroundColor="#aaaaaa" />
						<Image
							source={{
								uri: product.urlImagem
							}}
							style={styles.productImage}
						/>
						<Text style={styles.nameDescription}>{product.nome}</Text>
						<View style={styles.containerPrices}>
							<Text style={styles.priceOf}>{`De: ${replacePonitForComma(product.precoDe)}`}</Text>
							<Text style={styles.priceBy}>{`Por ${replacePonitForComma(product.precoPor)}`}</Text>
						</View>
						<View style={styles.containerDescription}>
							<Text style={styles.title}>{product.categoria.descricao}</Text>
							<Text style={styles.description}>{product.descricao.replace(/<br\/>+/g, '\n')}</Text>
						</View>
					</View>
				</ScrollView>
			) : (
				<Loading />
			)}
			<FloatingButton
				onPress={() =>
					Alert.alert(null, 'Produto reservado com sucesso', [
						{ text: 'OK', onPress: () => console.log('OK Pressed'), style: '#5e2a84' }
					])}
			/>
		</View>
	);
}

DescriptionProductScreen.navigationOptions = ({ navigation }) => {
	return {
		headerTitle: '',
		headerStyle: {
			backgroundColor: '#fff',
			marginTop: StatusBar.currentHeight,
			elevation: 0
		},
		headerTintColor: '#333'
	};
};

export default DescriptionProductScreen;
