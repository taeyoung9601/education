import {Link} from 'react-router-dom';


import styles from './Movie.module.css';


console.groupCollapsed('src/component/Movie.js'); console.groupEnd();

const Movie = (props) =>{
    console.groupCollapsed('Movie(',props,') invoked.'); console.groupEnd();

    // const location = useLocation();
    // console.log('location:', location);

    // const {state:movie} = location;
    // console.log('movie:', movie);

    return(
        <div className={styles.movie}>

            <img src={props.medium_cover_image} 
                alt={props.title} title={props.title} className={styles.movie__img}/>


            <div>
                <h2 className={styles.movie__title}>
                    <Link to ={`/movie/${props.id}`} state={props}>  {props.title}  </Link>
                </h2>

                <h3 className={styles.movie__year}>{props.year}</h3>

                <p className={styles.movie__summary}>
                    {
                    props.summary.length>250 ?

                    `${props.summary.slice(0,250)}...`
                    :
                    props.summary
                    }
                </p>

                <ul className={styles.movie__genres}>
                    {props.genres.map((g,i) => <li key={i}> {g} </li>)}
                </ul>

            </div>

        </div>

    )
};

export default Movie;

