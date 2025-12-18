import type { ThreeElements } from '@react-three/fiber';

export type TreeState = 'CHAOS' | 'FORMED';

export interface TreeColors {
  bottom: string;
  top: string;
}

export interface HandGesture {
  isOpen: boolean;
  position: { x: number; y: number }; // Normalized -1 to 1
  isDetected: boolean;
}

// Fix: Augment global JSX namespace to recognize React Three Fiber intrinsic elements
declare global {
  namespace JSX {
    interface IntrinsicElements extends ThreeElements {}
  }
}