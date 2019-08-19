import { StyleSheet } from 'react-native';

const Styles = StyleSheet.create({
  headerTitleContainer: { 
    flexDirection: 'row', 
    alignItems: 'center'
  },
  headerTitle: { 
    color: '#FFF', 
    fontSize: 16
  },
  headerMenu: { 
    padding: 8 
  },
  container: { 
    flex: 1
  },
  content: { 
    flex: 2, 
    alignItems: 'center', 
    justifyContent: 'center'
  },
  logo: { 
    color: '#4a4a4a', 
    fontFamily: 'Pacifico', 
    fontSize: 66 
  },
  footer: { 
    flex: 1, 
    alignItems: 'center', 
    justifyContent: 'flex-end' 
  },
  logoImage: { 
    height: 160, 
    width: 150 
  }
})

export default Styles;