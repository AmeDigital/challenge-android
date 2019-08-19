import { StatusBar } from 'react-native';
import { createStackNavigator, createDrawerNavigator, createAppContainer } from 'react-navigation';

import HomeScreen from '../pages/Home';
import AboutScreen from '../pages/About';
import CategoriesScreen from '../pages/Categories';
import DescriptionProductScreen from '../pages/DescriptionProduct';

import DrawerScreen from './DrawerScreen';

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
