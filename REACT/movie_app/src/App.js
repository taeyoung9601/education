import styles from"./App.module.css";
import {YTSMovies} from './component';

console.groupCollapsed('src/App.js'); console.groupEnd();


const App = (props) => {
  return (
    <div className={styles.container}>
      
      <h1 className={styles.title}>Movie App</h1>
      
      <p className={styles.paragraph}>Using External Movies of <strong className={styles.strong}>YTS</strong> in react</p>

      <YTSMovies/>
    </div>
  );
}

export default App;
