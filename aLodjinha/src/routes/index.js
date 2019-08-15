import React from 'react';
import { StatusBar, StyleSheet, View, Text, ScrollView, Image } from 'react-native';
import {
	createStackNavigator,
	createDrawerNavigator,
	createAppContainer,
	NavigationActions,
	DrawerActions
} from 'react-navigation';

import HomeScreen from '../pages/Home';
import AboutScreen from '../pages/About';
import CategoriesScreen from '../pages/Categories';
import DescriptionProductScreen from '../pages/DescriptionProduct';

class DrawerScreen extends React.Component {
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
						<Image source={require('../images/menu_pattern.png')} />
						<View style={styles.menu}>
							<View style={styles.menuItem}>
								<Image source={require('../images/home_menu.png')} />
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
										height: 80,
										width: 80,
										backgroundColor: '#ff0000',
										borderRadius: 50,
										position: 'absolute',
										top: -210,
										left: 9
									}}
								/>
								<Image
									source={require('../images/logo_menu.png')}
									style={{
										position: 'absolute',
										top: -200,
										left: 20
									}}
								/>
								<Text style={styles.menuText} onPress={this.navigateToScreen('Home')}>
									Home
								</Text>
							</View>
							<View style={styles.menuItem}>
								<Image source={require('../images/tag_menu.png')} />
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

const styles = StyleSheet.create({
	menu: {
		marginTop: 30
	},
	menuItem: {
		marginLeft: 25,
		flexDirection: 'row',
		alignItems: 'center',
		marginBottom: 30
	},
	menuText: {
		marginLeft: 20,
		fontWeight: 'bold',
		fontSize: 16,
		color: '#333'
	}
});

const AppStackNavigator = createStackNavigator(
	{
		Home: HomeScreen,
		About: AboutScreen,
		Categories: CategoriesScreen,
		DescriptionProduct: DescriptionProductScreen
	},
	{
		initialRouteName: 'Home',
		defaultNavigationOptions: {
			headerStyle: {
				backgroundColor: '#5e2a84',
				marginTop: StatusBar.currentHeight
			},
			headerTintColor: '#fff'
		}
	}
);

const AppDrawerNavigator = createDrawerNavigator(
	{
		AppLodjinha: AppStackNavigator
	},
	{
		contentComponent: DrawerScreen,
		drawerWidth: 300
	}
);

export default createAppContainer(AppDrawerNavigator);
