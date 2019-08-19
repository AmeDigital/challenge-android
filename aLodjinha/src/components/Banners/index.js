import React, { useState, useEffect } from 'react';
import Carousel from 'react-native-banner-carousel';
import { StyleSheet, Image, View, Dimensions, TouchableOpacity, Linking, Alert } from 'react-native';

import api from '../../services/api';
import Loading from '../Loading';

const BannerWidth = Dimensions.get('window').width;
const BannerHeight = 150;

export default function Banners() {
	const [ banners, setBanners ] = useState([]);

	useEffect(() => {
		(async function loadBanners() {
			const response = await api.get('/banner');
			setBanners(response.data.data);
		})();
	}, []);

	const uriSubmarino = 'https://www.submarino.com.br';

	function renderPage(image, index) {
		return (
			<View key={index}>
				<TouchableOpacity
					onPress={() =>
						Linking.openURL(uriSubmarino).catch((err) =>
							Alert.alert('Impossivel abrir a url', uriSubmarino)
						)}
				>
					<Image style={styles.image} source={{ uri: image.urlImagem }} />
				</TouchableOpacity>
			</View>
		);
	}

	return (
		<View style={styles.container}>
			{banners.length > 0 ? (
				<Carousel autoplay={false} index={0} pageSize={BannerWidth}>
					{banners.map((image, index) => renderPage(image, index))}
				</Carousel>
			) : (
				<Loading />
			)}
		</View>
	);
}

const styles = StyleSheet.create({
	container: {
		width: BannerWidth,
		height: BannerHeight,
		backgroundColor: '#ccc',
		justifyContent: 'center',
		elevation: 2
	},
	image: {
		width: BannerWidth,
		height: BannerHeight
	}
});
