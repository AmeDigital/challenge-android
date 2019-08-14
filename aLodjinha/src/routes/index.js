import { StatusBar } from 'react-native';
import { createStackNavigator, createAppContainer } from 'react-navigation';

import HomeScreen from '../pages/Home';
import AboutScreen from '../pages/About';
import CategoriesScreen from '../pages/Categories';

export default createAppContainer(
	createStackNavigator(
		{
			Home: HomeScreen,
			About: AboutScreen,
			Categories: CategoriesScreen
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
	)
);
