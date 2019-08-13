import React, { useState, useEffect } from 'react';
import Carousel from 'react-native-banner-carousel';
import { StyleSheet, Image, View, Dimensions, TouchableOpacity } from 'react-native';

import api from '../../services/api';

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

	function renderPage(image, index) {
		return (
			<View key={index}>
				<TouchableOpacity />
				<Image style={styles.image} source={{ uri: image.urlImagem }} />
			</View>
		);
	}

	return (
		<View style={styles.container}>
			<Carousel autoplay autoplayTimeout={5000} loop index={0} pageSize={BannerWidth}>
				{banners.map((image, index) => renderPage(image, index))}
			</Carousel>
		</View>
	);
}

const styles = StyleSheet.create({
	container: {
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
