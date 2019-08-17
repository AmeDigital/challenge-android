import React from 'react';
import { View, Text, ScrollView, Image } from 'react-native';
import { NavigationActions, DrawerActions } from 'react-navigation';

import styles from './styles';

export default class DrawerScreen extends React.Component {
	navigateToScreen = (route) => () => {
		const navigateAction = NavigationActions.navigate({
			routeName: route
		});
		this.props.navigation.dispatch(navigateAction);
		this.props.navigation.dispatch(DrawerActions.closeDrawer());
	};

	render() {
		return (
			<View>
				<ScrollView>
					<View>
						<Image source={require('../../images/menu_pattern.png')} />
						<View style={styles.menu}>
							<View style={styles.menuItem}>
								<Image source={require('../../images/home_menu.png')} />
								<Text
									style={{
										color: '#fff',
										position: 'absolute',
										top: -80,
										right: 10,
										fontFamily: 'Pacifico-Regular',
										fontSize: 22
									}}
								>
									a Lodjinha
								</Text>
								<View
									style={{
										height: 60,
										width: 60,
										backgroundColor: '#ff0000',
										borderRadius: 50,
										position: 'absolute',
										bottom: 120,
										right: 220
									}}
								/>
								<Image
									source={require('../../images/logo_menu.png')}
									style={{
										position: 'absolute',
										right: 232,
										bottom: 130
									}}
								/>
								<Text style={styles.menuText} onPress={this.navigateToScreen('Home')}>
									Home
								</Text>
							</View>
							<View style={styles.menuItem}>
								<Image source={require('../../images/tag_menu.png')} />
								<Text style={styles.menuText} onPress={this.navigateToScreen('About')}>
									Sobre o aplicativo
								</Text>
							</View>
						</View>
					</View>
				</ScrollView>
			</View>
		);
	}
}
