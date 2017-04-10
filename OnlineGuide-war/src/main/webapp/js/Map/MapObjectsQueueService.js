/**
 * Created by shaylin3 on 09.04.2017.
 */


angular.module('Map')
    .factory('MapObjectsQueueService', [
        function(){
            var self;
            function MapObjectsQueueService() {
                this._comparator = MapObjectsQueueService.DEFAULT_COMPARATOR;
                this._elements = [];
            }

                /**
                 * Compares `a` and `b`, when `a > b` it returns a positive number, when
                 * it returns 0 and when `a < b` it returns a negative number.
                 *
                 * @param {String|Number} a
                 * @param {String|Number} b
                 * @return {Number}
                 * @api public
             */
            MapObjectsQueueService.DEFAULT_COMPARATOR = function(a, b) {
                return a.distance - b.distance;
            };

            /**
             * Returns whether the priority queue is empty or not.
             *
             * @return {Boolean}
             * @api public
             */
            MapObjectsQueueService.prototype.isEmpty = function() {
                return this.size() === 0;
            };

            /**
             * Peeks at the top element of the priority queue.
             *
             * @return {Object}
             * @throws {Error} when the queue is empty.
             * @api public
             */
            MapObjectsQueueService.prototype.peek = function() {
                if (this.isEmpty()) throw new Error('MapObjectsQueueService is empty');

                return this._elements[0];
            };

            /**
             * Dequeues the top element of the priority queue.
             *
             * @return {Object}
             * @throws {Error} when the queue is empty.
             * @api public
             */

            /**
             * Enqueues the `element` at the priority queue and returns its new size.
             *
             * @param {Object} element
             * @return {Number}
             * @api public
             */
            MapObjectsQueueService.prototype.add = function(element) {
                var size = this._elements.push(element);
                var current = this._elements.length - 1;

                while (current - 1 >= 0 && (this._compare(current - 1, current) > 0 )) {
                    this._swap(current - 1, current);
                    current--;
                }

                return size;
            };

            /**
             * Returns the size of the priority queue.
             *
             * @return {Number}
             * @api public
             */
            MapObjectsQueueService.prototype.size = function() {
                return this._elements.length;
            };

            /**
             *  Iterates over queue elements
             *
             *  @param {Function} fn
             */
            MapObjectsQueueService.prototype.forEach = function(fn) {
                return this._elements.forEach(fn);
            };

            /**
             * Compares the values at position `a` and `b` in the priority queue using its
             * comparator function.
             *
             * @param {Number} a
             * @param {Number} b
             * @return {Number}
             * @api private
             */
            MapObjectsQueueService.prototype._compare = function(a, b) {
                return this._comparator(this._elements[a], this._elements[b]);
            };

            /**
             * Swaps the values at position `a` and `b` in the priority queue.
             *
             * @param {Number} a
             * @param {Number} b
             * @api private
             */
            MapObjectsQueueService.prototype._swap = function(a, b) {
                var aux = this._elements[a];
                this._elements[a] = this._elements[b];
                this._elements[b] = aux;
            };

            MapObjectsQueueService.prototype.contains = function(mapObject) {
                var contains = false;

                this.forEach(function(currentObject) {
                    if(mapObject.id == currentObject.id)
                        contains = true;
                });

                return contains;
            };

            MapObjectsQueueService.prototype.getNextNotPlayedObject = function() {
                for (var i = 0; i < this._elements.length; i++) {
                    if(!(this._elements[i].wasPlayed == true)){
                        this._elements[i].wasPlayed = true;

                        return this._elements[i];
                    }
                }
            };

            function equals(a, b) {
                // Create arrays of property names
                var aProps = Object.getOwnPropertyNames(a);
                var bProps = Object.getOwnPropertyNames(b);

                // If number of properties is different,
                // objects are not equivalent
                if (aProps.length != bProps.length) {
                    return false;
                }

                for (var i = 0; i < aProps.length; i++) {
                    var propName = aProps[i];

                    // If values of same property are not equal,
                    // objects are not equivalent
                    if (a[propName] !== b[propName]) {
                        return false;
                    }
                }

                // If we made it this far, objects
                // are considered equivalent
                return true;
            }

            return new MapObjectsQueueService();
        }]);