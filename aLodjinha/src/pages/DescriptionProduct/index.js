import React, { useEffect, useState } from 'react';
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
				<ScrollView>
					<View style={styles.productDescription}>
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
		title: navigation.getParam('descricao'),
		headerStyle: {
			backgroundColor: '#5e2a84',
			marginTop: StatusBar.currentHeight
		},
		headerTintColor: '#fff'
	};
};

export default DescriptionProductScreen;
