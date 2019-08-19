import React, { useEffect, useState } from 'react';
import { View, StatusBar, ScrollView, Alert } from 'react-native';

import api from '../../services/api';
import FloatingButton from '../../components/FloatingButton';

import Loading from '../../components/Loading';
import ProductDescription from '../../components/ProductDescription';

function DescriptionProductScreen({ navigation }) {
	const id = navigation.getParam('id');

	const [ product, setProduct ] = useState(undefined);
	const [ isLoading, setIsLoading ] = useState(false);

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
					<ProductDescription product={product} />
				</ScrollView>
			) : (
				<Loading />
			)}
			<FloatingButton
				loading={isLoading}
				onPress={() => {
					setIsLoading(true);
					api
						.post(`/produto/${product.id}`)
						.then((response) => {
							response.status === 200
								? Alert.alert(null, 'Produto reservado com sucesso', [
										{
											text: 'OK'
										}
									])
								: Alert.alert(null, 'Não foi possivel fazer a reserva', [
										{
											text: 'OK'
										}
									]);
						})
						.catch((error) => {
							Alert.alert(null, `Tivemos um problema ao fazer a reserva ${error}`, [ { text: 'OK' } ]);
						})
						.finally(() => setIsLoading(false));
				}}
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
