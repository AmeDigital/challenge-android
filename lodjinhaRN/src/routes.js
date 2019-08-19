import React from 'react';
import { 
  createDrawerNavigator, 
  createAppContainer, 
  createStackNavigator
} from 'react-navigation';

import DrawerIcon from './components/DrawerIcon';
import Drawer from './components/Drawer';

import Home from './Pages/Home';
import Category from './Pages/Category';
import About from './Pages/About';
import Product from './Pages/Product';

const AppStack = createStackNavigator(
  {
    Home,
    Category,
    Product
  },{
    navigationOptions: {
      drawerLabel: 'Home',
      drawerIcon: <DrawerIcon icon={require('./assets/images/home_menu.png')} />
    }
  }
);

const AboutStack = createStackNavigator(
  {
    About
  },{
    navigationOptions: {
      drawerLabel: 'Sobre o aplicativo',
      drawerIcon: <DrawerIcon icon={require('./assets/images/tag_menu.png')} />
    },
    defaultNavigationOptions: {
      headerTintColor: '#fff',
      headerStyle: {
        backgroundColor: '#5e2a84'
      }
    }
  }
);

const DrawerNavigator = createDrawerNavigator(
  {
    AppStack,
    AboutStack
  },
  {
    drawerBackgroundColor: '#FFF',
    overlayColor: '#4a4a4a',
    contentOptions: {
      activeTintColor: '#2d3142'
    },
    contentComponent: Drawer
  }
);

export default createAppContainer(DrawerNavigator);